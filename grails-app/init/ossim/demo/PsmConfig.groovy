package ossim.demo

import org.springframework.boot.context.properties.EnableConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationProperties
import org.springframework.boot.context.properties.ConfigurationPropertiesBinding
import org.springframework.core.convert.converter.Converter
import groovy.transform.ToString

@ConfigurationProperties(prefix="psm-view-params",ignoreInvalidFields=true)
@ToString(includeNames=true)
class PsmConfig {
  Map<String,Double> bbox
  List<Overlay> overlays

  @ToString(includeNames=true)
  static class Overlay {
    List<String> filename
    String title
    String url
    Boolean visable
  }

  @ConfigurationPropertiesBinding
  static class OverlayConverter implements Converter<Map<String, String>, Overlay>
  {

    @Override
    Overlay convert(Map<String, String> map)
    {
      return new Overlay( map )
    }
  }
}