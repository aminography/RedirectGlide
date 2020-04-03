package com.aminography.redirectglide

import java.io.IOException

/**
 * When an exception occurs during redirection, a [RedirectException] would be thrown.
 */
class RedirectException(
    val statusCode: Int,
    message: String?
) : IOException(message)