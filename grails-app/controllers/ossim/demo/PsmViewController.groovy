package ossim.demo

class PsmViewController {
  MapViewService mapViewService
  PsmConfig psmConfig

  def index() {
    [ psmViewParams: psmConfig ]
  }

  def getPsmTile() {
    render mapViewService?.getPsmTile( params )
  }
}
