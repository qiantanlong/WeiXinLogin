/*
 * Copyright (c) 2013. wyouflf (wyouflf@gmail.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.xutils.ex;

import android.text.TextUtils;

public class HttpException extends BaseException {
    private static final long serialVersionUID = 1L;

    private int code;
    private String customMessage;
    private String result;

    public HttpException() {
    }

    public HttpException(String detailMessage) {
        super(detailMessage);
    }

    public HttpException(String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
    }

    public HttpException(Throwable throwable) {
        super(throwable);
    }

    /**
     * @param code The http response status code, 0 if the http request error and has no response.
     */
    public HttpException(int code) {
        this.code = code;
    }

    /**
     * @param code          The http response status code, 0 if the http request error and has no response.
     * @param detailMessage
     */
    public HttpException(int code, String detailMessage) {
        super(detailMessage);
        this.code = code;
    }

    /**
     * @param code          The http response status code, 0 if the http request error and has no response.
     * @param detailMessage
     * @param throwable
     */
    public HttpException(int code, String detailMessage, Throwable throwable) {
        super(detailMessage, throwable);
        this.code = code;
    }

    /**
     * @param code      The http response status code, 0 if the http request error and has no response.
     * @param throwable
     */
    public HttpException(int code, Throwable throwable) {
        super(throwable);
        this.code = code;
    }

    public void setMessage(String message) {
        this.customMessage = message;
    }

    /**
     * @return The http response status code, 0 if the http request error and has no response.
     */
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        if (!TextUtils.isEmpty(customMessage)) {
            return customMessage;
        } else {
            return super.getMessage();
        }
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    @Override
    public String toString() {
        return "code: " + code + ", msg: " + getMessage() + ", result: " + result;
    }
}
