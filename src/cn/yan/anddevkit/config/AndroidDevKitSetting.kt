/**
 * MIT License
 *
 * Copyright (c) 2018 yanbo
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */
package cn.yan.anddevkit.config

import com.intellij.openapi.components.PersistentStateComponent
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.components.State
import com.intellij.openapi.components.Storage
import com.intellij.openapi.components.StoragePathMacros.APP_CONFIG

/**
 * 插件持久化数据处理
 */

@State(
        name = "AndroidDevKitSetting",
        storages = [Storage(
                id = "other",
                file = "$APP_CONFIG$/AndroidDevKitSetting.xml"
        )]
)

class AndroidDevKitSetting: PersistentStateComponent<AndroidDevKitSetting.Config> {
    data class Config(var workNetUrlMap: MutableMap<String, String> = mutableMapOf(),
                      var autoCheckInspectionValues: Boolean = false)

    var config: Config = Config()
        get() {
            if (field == null) {
                return Config()
            }
            return field
        }

    override fun getState(): Config? {
        return config
    }

    override fun loadState(config: Config) {
        this.config = config
    }

    companion object {
        fun getInstance(): AndroidDevKitSetting {
            return ServiceManager.getService(AndroidDevKitSetting::class.java)
        }
    }
}
