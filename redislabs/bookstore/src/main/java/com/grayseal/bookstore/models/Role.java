package com.grayseal.bookstore.models;

import lombok.Builder;
import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 * Represents a role entity stored in Redis.
 *
 * <p>This class is annotated with {@link Builder} to provide a builder pattern for creating instances, and
 * {@link Data} to generate getters, setters, equals, hashCode, and toString methods. The class is also annotated
 * with {@link RedisHash} to indicate that it is a Redis hash stored in Redis.</p>
 */
@Builder
@Data
@RedisHash
public class Role {

    /**
     * The unique identifier for the role.
     *
     * <p>This field is marked with {@link Id} to indicate it is the primary key in Redis.</p>
     */
    @Id
    private String id;

    /**
     * The name of the role.
     *
     * <p>This field is indexed to allow for efficient querying by role name.</p>
     */
    @Indexed
    private String name;
}