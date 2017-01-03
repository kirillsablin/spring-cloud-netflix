/*
 * Copyright 2013-2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.cloud.netflix.rx;

import org.springframework.http.ResponseEntity;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.context.request.async.WebAsyncUtils;
import org.springframework.web.method.support.ModelAndViewContainer;

import rx.Single;

/**
 * A specialized {@link AbstractRxReturnValueHandler} that handles {@link Single}
 * return types.
 *
 * @author Spencer Gibb
 * @author Jakub Narloch
 */
public class SingleReturnValueHandler extends AbstractRxReturnValueHandler {

	@Override
	protected boolean rxTypeIsAssignableFrom(Class<?> cls) {
		return Single.class.isAssignableFrom(cls);
	}

	@Override
	protected void startDeferredResultProcessing(Object returnValue, ModelAndViewContainer mavContainer,
												 NativeWebRequest webRequest, ResponseEntity<?> responseEntity)
			throws Exception {
		final Single<?> single = Single.class.cast(returnValue);
		WebAsyncUtils.getAsyncManager(webRequest).startDeferredResultProcessing(
				convertToDeferredResult(responseEntity, single), mavContainer);
	}

}
