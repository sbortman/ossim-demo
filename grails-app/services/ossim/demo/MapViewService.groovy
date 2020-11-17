package ossim.demo

import org.apache.commons.io.output.ByteArrayOutputStream as FastByteArrayOutputStream

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class MapViewService {
  OssimService ossimService

  def getMapParams( String filename, String entryid = '0' ) {
    def mapParams = ossimService.getImageInfo( filename, entryid )

    mapParams.filename = filename

    return mapParams
  }

  def getTile( Map<String, String> params ) {
    def getMapParams = parseGetMapParams( params )
    BufferedImage image = ossimService.getOrthoImage( getMapParams )
    FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream()

    ImageIO.write( image, 'png', new BufferedOutputStream( outputStream ) )

    [ contentType: 'image/png', file: outputStream.toInputStream() ]
  }

  def getPsmTile( Map<String, String> params ) {
    def getMapParams = parseGetMapParams( params )
    BufferedImage image = ossimService.getPsmImage( getMapParams )
    FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream()

    ImageIO.write( image, 'png', new BufferedOutputStream( outputStream ) )

    [ contentType: 'image/png', file: outputStream.toInputStream() ]
  }

  def get2CMVTile( Map<String, String> params ) {
    def getMapParams = parseGetMapParams( params )
    BufferedImage image = ossimService.get2cmvImage( getMapParams )
    FastByteArrayOutputStream outputStream = new FastByteArrayOutputStream()

    ImageIO.write( image, 'png', new BufferedOutputStream( outputStream ) )

    [ contentType: 'image/png', file: outputStream.toInputStream() ]
  }

  def parseGetMapParams( def params ) {
    [
        version: params?.find { it?.key?.equalsIgnoreCase( 'version' ) }?.value,
        layers : params?.find { it?.key?.equalsIgnoreCase( 'layers' ) }?.value,
        width  : params?.find { it?.key?.equalsIgnoreCase( 'width' ) }?.value?.toInteger(),
        height : params?.find { it?.key?.equalsIgnoreCase( 'height' ) }?.value?.toInteger(),
        srs    : params?.find { it?.key?.toLowerCase() in [ 'crs', 'srs' ] }?.value,
        bbox   : params?.find { it?.key?.equalsIgnoreCase( 'bbox' ) }?.value,
    ]
  }
}
