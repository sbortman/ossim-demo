package ossim.demo

class TwoCMVController {
  MapViewService mapViewService

  def index() {

    Boolean flag = params?.flag?.toBoolean() == true

    def twoCmvParams

    if ( flag ) {
      twoCmvParams = [
          bbox    : [ minY: -6.77636414675709, minX: -43.5749875896357, maxY: -6.78468298200278, maxX: -43.594024376172 ],
          overlays: [ [
                          filename: [
                              '/System/Volumes/Data/ossim/2cmv-test/20201111_175126_ssc14d2_0011_basic_panchromatic_dn_file_format.ntf',
                              '/System/Volumes/Data/ossim/2cmv-test/20200618_130630_ssc3d2_0008_basic_panchromatic_dn_file_format.ntf'
                          ].reverse(),
                          title   : '2CMV',
                          url     : '/twoCMV/get2CMVTile',
                          visible : false
                      ], [
                          filename: '/System/Volumes/Data/ossim/2cmv-test/20201111_175126_ssc14d2_0011_basic_panchromatic_dn_file_format.ntf',
                          title   : 'Image 2',
                          url     : '/mapView/getTile',
                          visible : false
                      ], [
                          filename: '/System/Volumes/Data/ossim/2cmv-test/20200618_130630_ssc3d2_0008_basic_panchromatic_dn_file_format.ntf',
                          title   : 'Image 1',
                          url     : '/mapView/getTile',
                          visible : true
                      ] ].reverse()
      ]
    } else {

      twoCmvParams = [
          bbox    : [ minY: 48.8208330630912, minX: 2.22276815344764, maxY: 48.9306217992998, maxX: 2.41349635027604 ],
          overlays: [ [
                          filename: [
                              '/System/Volumes/Data/ossim/2cmv-test/16MAY02111606-P1BS-055998375010_01_P013.tiff',
                              '/System/Volumes/Data/ossim/2cmv-test/16MAY02111607-P1BS-055998375010_01_P014.tiff'
                          ].reverse(),
                          title   : '2CMV',
                          url     : '/twoCMV/get2CMVTile',
                          visible : false
                      ], [
                          filename: '/System/Volumes/Data/ossim/2cmv-test/16MAY02111606-P1BS-055998375010_01_P013.tiff',
                          title   : 'Image 2',
                          url     : '/mapView/getTile',
                          visible : false
                      ], [
                          filename: '/System/Volumes/Data/ossim/2cmv-test/16MAY02111607-P1BS-055998375010_01_P014.tiff',
                          title   : 'Image 1',
                          url     : '/mapView/getTile',
                          visible : true
                      ] ].reverse()

      ]
    }

    [ twoCmvParams: twoCmvParams ]
  }

  def get2CMVTile() {
    render mapViewService.get2CMVTile( params )
  }
}
