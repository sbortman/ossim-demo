package ossim.demo

class PsmViewController {
  MapViewService mapViewService

  def index() {
    def psmViewParams = [
        bbox    : [ minX: 147.166269578023, minY: -42.9405015289975, maxX: 147.352799864668, maxY: -42.7825465458196 ],
        overlays: [ [
                        filename: [
                            '/System/Volumes/Data/ossim/psm-test/5V090205P0001912264B220000100282M_001508507.ntf',
                            '/System/Volumes/Data/ossim/psm-test/5V090205M0001912264B220000100072M_001508507.ntf'
                        ].reverse(),
                        title   : 'PSM',
                        url     : '/psmView/getPsmTile',
                        visible : false
                    ], [
                        filename: '/System/Volumes/Data/ossim/psm-test/5V090205P0001912264B220000100282M_001508507.ntf',
                        title   : 'Pan',
                        url     : '/mapView/getTile',
                        visible : false
                    ], [
                        filename: '/System/Volumes/Data/ossim/psm-test/5V090205M0001912264B220000100072M_001508507.ntf',
                        title   : 'Color',
                        url     : '/mapView/getTile',
                        visible : true
                    ] ].reverse()
    ]

    [ psmViewParams: psmViewParams ]
  }

  def getPsmTile() {
    render mapViewService.getPsmTile( params )
  }
}
