/*
 * Copyright 2002-2008 the original author or authors.
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

package org.springframework.integration.transformer;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

import org.springframework.integration.message.Message;

/**
 * @author Mark Fisher
 */
public class MessageTransformerChain implements MessageTransformer {

	private final List<MessageTransformer> transformers = new CopyOnWriteArrayList<MessageTransformer>();


	/**
	 * Add a transformer to the end of the chain.
	 */
	public void add(MessageTransformer transformer) {
		this.transformers.add(transformer);
	}

	/**
	 * Add a transformer to the chain at the specified index.
	 */
	public void add(int index, MessageTransformer transformer) {
		this.transformers.add(index, transformer);
	}

	public void setTransformers(List<MessageTransformer> transformers) {
		this.transformers.clear();
		this.transformers.addAll(transformers);
	}

	public void transform(Message<?> message) {
		for (MessageTransformer next : transformers) {
			next.transform(message);
		}
	}

}
