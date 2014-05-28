/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 * contributor license agreements.  See the NOTICE file distributed with
 * this work for additional information regarding copyright ownership.
 * The ASF licenses this file to You under the Apache License, Version 2.0
 * (the "License"); you may not use this file except in compliance with
 * the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.jclouds.openstack.keystone.v2_0.domain;

import static com.google.common.base.Preconditions.checkNotNull;

import java.beans.ConstructorProperties;
import java.util.Date;

import org.jclouds.javax.annotation.Nullable;

import com.google.common.base.Objects;
import com.google.common.base.Objects.ToStringHelper;
import com.google.common.base.Optional;

/**
 * This is the Keystone representation of AWS/EC2/S3 style credentials.
 *
 * @author Jasdeep Hundal
 */
public class KeystoneAWSCredential implements Comparable<KeystoneAWSCredential> {

    public static Builder<?> builder() {
        return new ConcreteBuilder();
    }

    public Builder<?> toBuilder() {
        return new ConcreteBuilder().fromToken(this);
    }

    public abstract static class Builder<T extends Builder<T>> {

        protected abstract T self();
        protected String access;
        protected String secret;
        protected String tenantId;
        protected String userId;

        /**
         * @see KeystoneAWSCredential#getAccess()
         */
        public T access(String access) {
            this.access = access;
            return self();
        }

        /**
         * @see KeystoneAWSCredential#getSecret()
         */
        public T secret(String secret) {
            this.secret = secret;
            return self();
        }

        /**
         * @see KeystoneAWSCredential#getTenantId()
         */
        public T tenantId(String tenantId) {
            this.tenantId = tenantId;
            return self();
        }

        /**
         * @see KeystoneAWSCredential#getUserId()
         */
        public T userId(String userId) {
            this.userId = userId;
            return self();
        }


        public KeystoneAWSCredential build() {
            return new KeystoneAWSCredential(access, secret, tenantId, userId);
        }

        public T fromToken(KeystoneAWSCredential in) {
            return this
                    .access(in.access)
                    .secret(in.secret)
                    .tenantId(in.tenantId)
                    .userId(in.userId);
        }
    }

    private static class ConcreteBuilder extends Builder<ConcreteBuilder> {

        @Override
        protected ConcreteBuilder self() {
            return this;
        }
    }
    private final String access;
    private final String secret;
    private final String tenantId;
    private final String userId;

    @ConstructorProperties({
        "access", "secret", "tenant_id", "user_id"
    })
    protected KeystoneAWSCredential(String access, String secret, String tenantId, String userId) {
        this.access = checkNotNull(access, "access key was null");
        this.secret = checkNotNull(secret, "secret key was null");
        this.tenantId = checkNotNull(tenantId, "tenant id was null");
        this.userId = checkNotNull(userId, "tenant id was null");
    }

    public String getAccess() {
        return this.access;
    }

    public String getSecret() {
        return this.secret;
    }

    public String getUserId() {
        return this.userId;
    }

    public String getTenantId() {
        return this.tenantId;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(access, secret, tenantId, userId);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;
        KeystoneAWSCredential that = KeystoneAWSCredential.class.cast(obj);
        return Objects.equal(this.access, that.access)
                && Objects.equal(this.secret, that.secret)
                && Objects.equal(this.tenantId, that.tenantId)
                && Objects.equal(this.userId, that.userId);
    }

    protected ToStringHelper string() {
        return Objects.toStringHelper(this).omitNullValues()
                .add("access", access).add("secret", secret).add("tenantId", tenantId).add("userId", userId);
    }

    @Override
    public String toString() {
        return string().toString();
    }

    // This is merely to preserve *some* ordering in case something in JClouds (or anyone else) needs it
    @Override
    public int compareTo(KeystoneAWSCredential that) {
        if (that == null)
            return 1;
        if (this == that)
            return 0;
        return this.access.compareTo(that.access);
    }
}
