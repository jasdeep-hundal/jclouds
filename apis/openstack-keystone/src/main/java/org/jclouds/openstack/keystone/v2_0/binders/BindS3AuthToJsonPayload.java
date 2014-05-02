/*
 * Copyright 2014 The Apache Software Foundation.
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
package org.jclouds.openstack.keystone.v2_0.binders;

import static com.google.common.base.Preconditions.checkArgument;
import static com.google.common.base.Preconditions.checkNotNull;

import java.util.Map;

import javax.inject.Inject;
import javax.inject.Singleton;

import org.jclouds.http.HttpRequest;
import org.jclouds.json.Json;
import org.jclouds.rest.MapBinder;
import org.jclouds.rest.binders.BindToJsonPayload;
import org.jclouds.rest.internal.GeneratedHttpRequest;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableMap.Builder;

/**
 * 
 * @author Jasdeep Hundal
 * 
 */
@Singleton
public class BindS3AuthToJsonPayload extends BindToJsonPayload implements MapBinder {
   @Inject
   public BindS3AuthToJsonPayload(Json jsonBinder) {
      super(jsonBinder);
   }

   @Override
   public <R extends HttpRequest> R bindToRequest(R request, Object toBind) {
      throw new IllegalStateException("BindAuthToJsonPayload needs parameters");
   }
   /*
   protected void addCredentialsInArgsOrNull(GeneratedHttpRequest gRequest, Builder<String, Object> builder) {
      for (Object arg : Iterables.filter(gRequest.getInvocation().getArgs(), Predicates.notNull())) {
         if (arg.getClass().isAnnotationPresent(CredentialType.class)) {
            builder.put(arg.getClass().getAnnotation(CredentialType.class).value(), arg);
         }
      }
   }
   */

   @Override
   public <R extends HttpRequest> R bindToRequest(R request, Map<String, Object> postParams) {
      checkArgument(checkNotNull(request, "request") instanceof GeneratedHttpRequest,
               "this binder is only valid for GeneratedHttpRequests!");
      GeneratedHttpRequest gRequest = (GeneratedHttpRequest) request;

      Builder<String, Object> builder = ImmutableMap.builder();
      //addCredentialsInArgsOrNull(gRequest, builder);
      // TODO: is tenantName permanent? or should we switch to tenantId at some point. seems most tools
      // still use tenantName
      builder.put("access", postParams.get("access"));
      builder.put("token", postParams.get("token"));
      builder.put("signature", postParams.get("signature"));
      return super.bindToRequest(request, ImmutableMap.of("credentials", builder.build()));
   }

}
