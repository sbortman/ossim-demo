package ossim.demo

import joms.oms.Init
import org.geotools.util.factory.Hints
import org.locationtech.jts.geom.Geometry
import org.locationtech.jts.geom.GeometryCollection
import geoscript.GeoScript
import geoscript.geom.Bounds
import grails.converters.JSON
import groovy.json.JsonSlurper

import geoscript.feature.Feature

class BootStrap {

  def init = { servletContext ->
    Init.instance().initialize()

    Hints.putSystemDefault( Hints.FORCE_LONGITUDE_FIRST_AXIS_ORDER, Boolean.TRUE )

    JSON.registerObjectMarshaller( Bounds ) {
      [minX: it.minX, minY: it.minY, maxX: it.maxX, maxY: it.maxY, srs: it.proj.id]
    }

    JSON.registerObjectMarshaller( Geometry ) {
      def json = GeoScript.wrap( it ).geoJSON

      new JsonSlurper().parseText( json )
    }

    JSON.registerObjectMarshaller( GeometryCollection ) {
      def json = GeoScript.wrap( it ).geoJSON

      new JsonSlurper().parseText( json )
    }

    JSON.registerObjectMarshaller( Feature ) {
      new JsonSlurper().parseText( it.geoJSON )
    }
  }

  def destroy = {
  }
}
