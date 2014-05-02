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
package org.jclouds.openstack.keystone.v2_0.features;


import javax.inject.Named;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;

import org.jclouds.Fallbacks.NullOnNotFoundOr404;
import org.jclouds.openstack.keystone.v2_0.binders.BindS3AuthToJsonPayload;
import org.jclouds.openstack.keystone.v2_0.domain.Token;
import org.jclouds.openstack.keystone.v2_0.filters.AuthenticateRequest;
import org.jclouds.openstack.v2_0.services.Identity;
import org.jclouds.rest.annotations.Fallback;
import org.jclouds.rest.annotations.MapBinder;
import org.jclouds.rest.annotations.PayloadParam;
import org.jclouds.rest.annotations.RequestFilters;
import org.jclouds.rest.annotations.SelectJson;

import com.google.common.util.concurrent.ListenableFuture;

/**
 * Provides asynchronous access to the Keystone S3 authentication API via their REST API.
 * <p/>
 *
 * @see S3Api
 * @see <a href=
 *       "https://github.com/openstack/keystone/tree/master/keystone/contrib/s3"
 *      />
 * @author Jasdeep Hundal
 */
@org.jclouds.rest.annotations.Endpoint(Identity.class)
public interface S3AsyncApi {

   
   /** @see S3Api#get(String) */
   @Named("s3token:get")
   @POST
   @SelectJson("token")
   @Consumes(MediaType.APPLICATION_JSON)
   @Path("/s3tokens")
   @RequestFilters(AuthenticateRequest.class)
   @MapBinder(BindS3AuthToJsonPayload.class)
   @Fallback(NullOnNotFoundOr404.class)
   ListenableFuture<? extends Token> get(@PayloadParam("access") String access,
                                         @PayloadParam("token") String token,
                                         @PayloadParam("signature") String signature);
}
