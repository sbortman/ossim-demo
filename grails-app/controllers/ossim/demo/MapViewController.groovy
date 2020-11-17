package ossim.demo

class MapViewController {
  MapViewService mapViewService

  def index(String filename) {

    def mapViewParams = mapViewService.getMapParams( filename )

    [ mapViewParams: mapViewParams]
  }

  def getTile() {
    render mapViewService.getTile( params )
  }
}
