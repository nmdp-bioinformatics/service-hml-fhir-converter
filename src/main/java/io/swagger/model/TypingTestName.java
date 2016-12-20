package io.swagger.model;

import java.util.Objects;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonCreator;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;




/**
 * TypingTestName
 */
@javax.annotation.Generated(value = "class io.swagger.codegen.languages.SpringCodegen", date = "2016-12-20T13:45:00.535-06:00")

public class TypingTestName   {
  private String id = null;

  private String name = null;

  public TypingTestName id(String id) {
    this.id = id;
    return this;
  }

   /**
   * GUID id of testing type name
   * @return id
  **/
  @ApiModelProperty(value = "GUID id of testing type name")
  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public TypingTestName name(String name) {
    this.name = name;
    return this;
  }

   /**
   * Display / Common name of typing test
   * @return name
  **/
  @ApiModelProperty(value = "Display / Common name of typing test")
  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }


  @Override
  public boolean equals(java.lang.Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    TypingTestName typingTestName = (TypingTestName) o;
    return Objects.equals(this.id, typingTestName.id) &&
        Objects.equals(this.name, typingTestName.name);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name);
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    sb.append("class TypingTestName {\n");
    
    sb.append("    id: ").append(toIndentedString(id)).append("\n");
    sb.append("    name: ").append(toIndentedString(name)).append("\n");
    sb.append("}");
    return sb.toString();
  }

  /**
   * Convert the given object to string with each line indented by 4 spaces
   * (except the first line).
   */
  private String toIndentedString(java.lang.Object o) {
    if (o == null) {
      return "null";
    }
    return o.toString().replace("\n", "\n    ");
  }
}

