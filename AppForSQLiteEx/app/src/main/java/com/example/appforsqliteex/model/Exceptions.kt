package com.example.appforsqliteex.model

open class AppException: RuntimeException()


class EmptyFieldException(val field: User): AppException()


class PasswordMissMatchException: AppException()


class AccountAlreadyExistsException: AppException()


class AuthException: AppException()

class StorageException: AppException()