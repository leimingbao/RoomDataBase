package com.leiming.roomdatabaselog

import com.leiming.roomdatabaselog.database.dao.LoggingDao
import com.leiming.roomdatabaselog.database.dao.LoggingHeaderDao
import com.leiming.roomdatabaselog.database.entity.LoggingEntity
import com.leiming.roomdatabaselog.database.entity.LoggingHeaderEntity
import com.leiming.roomdatabaselog.database.entity.LoggingRequestEntity
import com.leiming.roomdatabaselog.database.entity.LoggingResponseEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import okhttp3.Request
import okhttp3.Response
import okhttp3.ResponseBody

class LoggingIntercept constructor(
        private val loggingDao: LoggingDao,
        private val loggingHeaderDao: LoggingHeaderDao
) : AbsLoggingIntercept() {
    private var relationKey: String? = null
    private var responseBody: String? = null
    private var moduleName: String? = null
    private var moduleColor: String? = null
    private var ext: String? = null
    private var info: StringBuffer = StringBuffer()

    private var loggingEntity: LoggingEntity? = null
    private var requestEntity: LoggingRequestEntity? = null
    private var responseEntity: LoggingResponseEntity? = null
    private var loggingHeaderEntity: LoggingHeaderEntity? = null

    override fun process(startReq: Long, request: Request, endReq: Long, response: Response) {
        prepare(request, response, startReq)

        requestEntity =
                LoggingRequestEntity(
                        startReq,
                        request.url.toString(),
                        request.method
                )

        responseEntity =
                LoggingResponseEntity(
                        endReq,
                        response.code,
                        responseBody
                )

        loggingEntity =
                LoggingEntity(
                        relationKey!!,
                        moduleName,
                        moduleColor,
                        ext,
                        requestEntity!!,
                        responseEntity
                )
    }

    override fun insert(request: Request) {
        GlobalScope.launch(Dispatchers.IO) {
            loggingDao.insertLogging(loggingEntity!!)

            request.apply {
                for (name in this.headers.names()) {
                    loggingHeaderEntity = LoggingHeaderEntity(
                            relationKey!!,
                            name,
                            this.headers[name]
                    )
                    loggingHeaderDao.insertLogging(loggingHeaderEntity!!)
                }
            }
        }
    }

    override fun printInfo(request: Request, startReq: Long, endReq: Long) {
        info = StringBuffer()
        info.append("[Module Name]: $moduleName")
        info.append("\n[Url]: " + request.url)
        info.append("\n[耗时]: " + (endReq - startReq) + "ms ")
        info.append("\n[responseBody]: $responseBody")
        println("$info")
    }

    private fun prepare(request: Request, response: Response, startReq: Long) {
        val responseStr: ResponseBody = response.peekBody(1024 * 1024.toLong())
        responseBody = responseStr.string()
        relationKey = (request.hashCode() + startReq).toString()
    }

    fun setModuleName(name: String): LoggingIntercept {
        moduleName = name
        return this
    }

    fun setModuleColor(color: String): LoggingIntercept {
        moduleColor = color
        return this
    }

    fun setExtraInfo(extra: String): LoggingIntercept {
        ext = extra
        return this
    }
}