package br.com.forum_api.forum_api.mapper

interface Mapper<T, U> {

    fun map(t: T): U
}