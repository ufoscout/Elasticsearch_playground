/*******************************************************************************
 * Copyright 2017 Francesco Cina'
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *   http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 ******************************************************************************/
package com.example.demo.config;

import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig.Builder;
import org.elasticsearch.client.RestClient;
import org.elasticsearch.client.RestClientBuilder.RequestConfigCallback;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticSearchConfig {

	private final String host = "127.0.0.1";
	private final int port = 9200;

    @Bean(destroyMethod = "close")
    public RestClient transportClient() {
        return RestClient
            .builder(new HttpHost(host, port))
            .setRequestConfigCallback(new RequestConfigCallback() {
                  @Override
                  public Builder customizeRequestConfig(Builder builder) {
                      return builder
                          .setConnectTimeout(1000)
                          .setSocketTimeout(5000);
                  }
            })
            .setMaxRetryTimeoutMillis(60000)
            .build();
    }

}
