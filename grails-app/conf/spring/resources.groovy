// Place your Spring DSL code here

import ossim.demo.PsmConfig
import ossim.demo.TwoCmvConfig

beans = {
  psmConfig( PsmConfig )
  psmConfigOverlayConverter( PsmConfig.OverlayConverter )
  twoCmvConfig( TwoCmvConfig )

}
