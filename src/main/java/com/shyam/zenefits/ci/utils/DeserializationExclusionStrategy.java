package com.shyam.zenefits.ci.utils;

import java.lang.annotation.Annotation;
import java.util.Collection;

import org.springframework.util.CollectionUtils;

import com.google.gson.ExclusionStrategy;
import com.google.gson.FieldAttributes;
import com.google.gson.annotations.Expose;

public class DeserializationExclusionStrategy implements ExclusionStrategy{

	@Override
	public boolean shouldSkipField(FieldAttributes f) {
		Collection<Annotation> annotations = f.getAnnotations();
		if (!CollectionUtils.isEmpty(annotations)) {
			for (Annotation annotation : annotations) {
				if (annotation instanceof Expose) {
					return true;
				}
			}
		}
		return false;
	}

	@Override
	public boolean shouldSkipClass(Class<?> clazz) {
		return false;
	}

}
