package com.rafaelfelipeac.kotlinrepo.core

import com.rafaelfelipeac.kotlinrepo.base.equalTo
import com.rafaelfelipeac.kotlinrepo.core.plataform.Config
import org.junit.Test

class ConfigTest {

    @Test
    fun `URL_BASE_GITHUB configuration must be correct`() {
        Config.URL_BASE_GITHUB equalTo "https://api.github.com/"
    }

    @Test
    fun `LANGUAGE configuration must be correct`() {
        Config.LANGUAGE equalTo "language:kotlin"
    }

    @Test
    fun `SORT configuration must be correct`() {
        Config.SORT equalTo "star"
    }
}
