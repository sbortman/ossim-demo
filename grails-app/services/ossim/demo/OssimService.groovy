package ossim.demo

import geoscript.geom.io.WktReader
import geoscript.proj.Projection
import joms.oms.DataInfo
import org.ossim.oms.util.TransparentFilter

import javax.imageio.ImageIO
import java.awt.image.BufferedImage

class OssimService {

  TransparentFilter transparentFilter = new TransparentFilter()

  def getImageInfo( String filename, String entryid = '0' ) {
    def info = DataInfo.readInfo( filename )
    def oms = new XmlSlurper().parseText( info )
    def wktReader = new WktReader()

    def imageMetadata = oms?.dataSets?.RasterDataSet?.rasterEntries?.RasterEntry?.collect { rasterEntry ->
      def bbox = wktReader.read( rasterEntry?.groundGeom?.text() )?.bounds

      bbox.proj = new Projection( rasterEntry?.groundGeom?.@srs?.text() )

      return [
          filename     : rasterEntry?.filename?.text(),
          entryId      : rasterEntry?.entryId?.text(),
          width        : rasterEntry?.width?.text()?.toInteger(),
          height       : rasterEntry.height?.text()?.toInteger(),
          numberOfBands: rasterEntry?.numberOfBands?.text()?.toInteger(),
          dataType     : rasterEntry.dataType,
          bitDepth     : rasterEntry.bitDepth?.text()?.toInteger(),
          bbox         : bbox
      ]
    }

    imageMetadata[ entryid?.toInteger() ]
  }

  BufferedImage getOrthoImage( def getMapParams ) {
    def outputFile = File.createTempFile( 'chipper-', '.png', '/tmp' as File )

    String cmd = [
        'ossim-chipper',
        '--op', 'ortho',
        '--histogram-op', 'auto-minmax',
        '--cut-height', getMapParams?.height as String,
        '--cut-width', getMapParams?.width as String,
        '--srs', getMapParams?.srs,
        '--cut-wms-bbox', getMapParams?.bbox,
        '--bands', 'default',
        '--output-radiometry', 'U8',
        '--writer-prop', 'create_external_geometry=false',
        '--resample-filter', 'bilinear',
        getMapParams?.layers,
        outputFile?.absolutePath
    ].join( ' ' )

    println cmd

    def p = cmd.execute()
    def stdout = new StringBuffer()
    def stderr = new StringBuffer()

    p.consumeProcessOutput( stdout, stderr )

    def x = p.waitFor()

    if ( x ) {
      System.err.println( stderr )
    } else {
      println stdout
    }

    BufferedImage image = ImageIO.read( outputFile )

    image = TransparentFilter.fixTransparency( transparentFilter, image )
    outputFile.delete()

    return image
  }

  BufferedImage getPsmImage( def getMapParams ) {
    def outputFile = File.createTempFile( 'chipper-', '.png', '/tmp' as File )

    String cmd = [
        'ossim-chipper',
        '--op', 'psm',
        '--histogram-op', 'auto-minmax',
        '--cut-height', getMapParams?.height as String,
        '--cut-width', getMapParams?.width as String,
        '--srs', getMapParams?.srs,
        '--cut-wms-bbox', getMapParams?.bbox,
        '--bands', 'default',
        '--output-radiometry', 'U8',
        '--writer-prop', 'create_external_geometry=false',
        '--resample-filter', 'bilinear',
        getMapParams?.layers?.split( ',' )?.join( ' ' ),
        outputFile?.absolutePath
    ].join( ' ' )

    println cmd

    def p = cmd.execute()
    def stdout = new StringBuffer()
    def stderr = new StringBuffer()

    p.consumeProcessOutput( stdout, stderr )

    def x = p.waitFor()

    if ( x ) {
      System.err.println( stderr )
    } else {
      println stdout
    }

    BufferedImage image = ImageIO.read( outputFile )

    image = TransparentFilter.fixTransparency( transparentFilter, image )
    outputFile.delete()

    return image
  }

  BufferedImage get2cmvImage( def getMapParams ) {
    def outputFile = File.createTempFile( 'chipper-', '.png', '/tmp' as File )

    String cmd = [
        'ossim-chipper',
        '--op', '2cmv',
        '--histogram-op', 'auto-minmax',
        '--cut-height', getMapParams?.height as String,
        '--cut-width', getMapParams?.width as String,
        '--srs', getMapParams?.srs,
        '--cut-wms-bbox', getMapParams?.bbox,
        '--bands', 'default',
        '--output-radiometry', 'U8',
        '--writer-prop', 'create_external_geometry=false',
        '--resample-filter', 'bilinear',
        getMapParams?.layers?.split( ',' )?.join( ' ' ),
        outputFile?.absolutePath
    ].join( ' ' )

    println cmd

    def p = cmd.execute()
    def stdout = new StringBuffer()
    def stderr = new StringBuffer()

    p.consumeProcessOutput( stdout, stderr )

    def x = p.waitFor()

    if ( x ) {
      System.err.println( stderr )
    } else {
      println stdout
    }

    BufferedImage image = ImageIO.read( outputFile )

    image = TransparentFilter.fixTransparency( transparentFilter, image )
    outputFile.delete()

    return image
  }

}
