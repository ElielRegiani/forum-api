package br.com.forum_api.forum_api.security

import br.com.forum_api.forum_api.config.JWTUtils
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.web.filter.OncePerRequestFilter

class JWTAuthenticationFilter(
    private val jwtUtil: JWTUtils
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val token = request.getHeader("Authorization")
        val jwt = getTokenDetail(token)

        if (jwtUtil.isValid(token)) {
            val authentication = jwtUtil.getAuthentication(jwt)
            SecurityContextHolder.getContext().authentication = authentication
        }

        filterChain.doFilter(request, response)
    }

    private fun getTokenDetail(token: String?) : String? {
        return token?.let { jwt ->
            jwt.startsWith("Bearer ")
            jwt.substring(7, token.length)
        }
    }
}
