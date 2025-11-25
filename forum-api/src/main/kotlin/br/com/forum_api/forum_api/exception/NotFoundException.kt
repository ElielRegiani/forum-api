package br.com.forum_api.forum_api.exception

import java.lang.RuntimeException

class NotFoundException(message: String?): RuntimeException(message) {

}