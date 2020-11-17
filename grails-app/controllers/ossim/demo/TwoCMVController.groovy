package ossim.demo

class TwoCMVController {
  MapViewService mapViewService
  TwoCmvConfig twoCmvConfig

  def index() {

    println twoCmvConfig

    [ twoCmvParams: twoCmvConfig ]
  }

  def get2CMVTile() {
    render mapViewService.get2CMVTile( params )
  }
}
