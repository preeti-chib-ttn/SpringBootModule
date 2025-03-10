package com.ttn.bootcamp.util.mixin;

import com.fasterxml.jackson.annotation.JsonFilter;

// Object mapper uses this to add this filter to user entity dynamically
// hence we can use it for special use case without changing entity and dto logic
@JsonFilter("UserDynamicFilter")
public abstract class UserMixin {
}
