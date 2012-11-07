package com.simple.jerkson

import com.fasterxml.jackson.databind.{MappingJsonFactory, ObjectMapper}
import com.fasterxml.jackson.core.{JsonGenerator, JsonParser => JacksonParser}
import com.fasterxml.jackson.datatype.joda.JodaModule

object Json extends Json

trait Json extends Parser with Generator {
  protected val classLoader = Thread.currentThread().getContextClassLoader

  protected val mapper = new ObjectMapper
  mapper.registerModule(new ScalaModule(classLoader))
  mapper.registerModule(new JodaModule)

  protected val factory = new MappingJsonFactory(mapper)
  factory.enable(JsonGenerator.Feature.AUTO_CLOSE_JSON_CONTENT)
  factory.enable(JsonGenerator.Feature.AUTO_CLOSE_TARGET)
  factory.enable(JsonGenerator.Feature.QUOTE_FIELD_NAMES)
  factory.enable(JacksonParser.Feature.ALLOW_COMMENTS)
  factory.enable(JacksonParser.Feature.AUTO_CLOSE_SOURCE)
}