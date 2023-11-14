package xyz.dudedayaworks.jetpackcompose.playground.data.auth


val NotAuthenticated = object : Exception("Not authenticated") {}
val TokenExpired = object : Exception("Session expired") {}