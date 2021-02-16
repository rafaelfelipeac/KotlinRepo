package com.rafaelfelipeac.marvelapp.core

import com.rafaelfelipeac.marvelapp.base.equalTo
import com.rafaelfelipeac.marvelapp.core.plataform.Config
import org.junit.Test

class ConfigTest {

    @Test
    fun `URL_BASE_GITHUB configuration must be correct`() {
        Config.URL_BASE_MARVEL equalTo "http://gateway.marvel.com/v1/public/"
    }
}
