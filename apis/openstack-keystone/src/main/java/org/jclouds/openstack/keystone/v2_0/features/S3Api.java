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

import org.jclouds.openstack.keystone.v2_0.domain.Token;

import com.google.common.annotations.Beta;
import com.google.common.collect.FluentIterable;
import org.jclouds.openstack.keystone.v2_0.domain.KeystoneAWSCredential;

/**
 * Provides synchronous access to the KeyStone S3 API.
 * <p/>
 *
 * @author Jasdeep Hundal
 * @see S3AsyncApi
 * @see <a href=
 *       "https://github.com/openstack/keystone/tree/master/keystone/contrib/s3"
 *      />
 */
@Beta
public interface S3Api {

   /**
    * Validate an S3 request and, if it is valid, return access information regarding the tenant (though not the service catalog)/
    *
    * @return the requested information
    */
   Token get(String access, String token, String signature);

   /**
    * Get the set of AWS style credentials associated with the user whose id is supplied as the argument
    *
    * @return the requested information
    */
   FluentIterable<? extends KeystoneAWSCredential> getAWSCredsForUser(String id);
}
