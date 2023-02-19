package com.olaaref.photoapp.apigateway.security

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.core.env.Environment

//@Component
class AuthorizationFilter @Autowired constructor(val environment: Environment) {

    private val logger: Logger = LoggerFactory.getLogger(AuthorizationFilter::class.java)

//    override fun filter(exchange: ServerWebExchange?, chain: GatewayFilterChain?): Mono<Void> {
//        val request = exchange?.request
//
//        logData(request!!)
//        val authHeader = request?.headers?.get(HttpHeaders.AUTHORIZATION)?.first()
//        if (authHeader == null || !authHeader.startsWith("Bearer ")){
//            return chain?.filter(exchange)!!
//        }
//
//        val authentication = getAuthentication(request)
//        SecurityContextHolder.getContext().authentication = authentication
//
//        return chain?.filter(exchange)!!
//    }
//
//    private fun getAuthentication(request: ServerHttpRequest): UsernamePasswordAuthenticationToken?{
//        val authHeader = request.headers[HttpHeaders.AUTHORIZATION]?.first()
//        val token = authHeader?.replace("Bearer ", "")
//
//        val userId = Jwts.parserBuilder()
//            .setSigningKey(environment.getProperty("token.secret"))
//            .build()
//            .parseClaimsJws(token)
//            .body
//            .subject;
//
//        return when(userId){
//            null -> null
//            else -> UsernamePasswordAuthenticationToken(userId, null, emptyList())
//        }
//
//
//    }
//
//    private fun logData(request: ServerHttpRequest) {
//        logger.info("=======================> Calling api [{}]", request.uri.path)
//    }
}