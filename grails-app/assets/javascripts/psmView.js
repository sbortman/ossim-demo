//= require jquery-3.3.1.min.js
//= require webjars/openlayers/4.6.5/ol.js
//= require webjars/ol-layerswitcher/dist/ol-layerswitcher.js
//= require_self

var PsmView = (function () {
    "use strict";

    function init(params) {
        var layers = [
            new ol.layer.Group({
                // A layer must have a title to appear in the layerswitcher
                title: 'Base maps',
                layers: [
                    new ol.layer.Tile({
                        // A layer must have a title to appear in the layerswitcher
                        title: 'Reference',
                        // Again set this layer as a base layer
                        type: 'base',
                        visible: true,
                        source: new ol.source.TileWMS({
                            url: 'https://omar-dev.ossim.io/omar-mapproxy/service',
                            params: {
                                'LAYERS': 'o2-basemap-basic'
                            }
                        })
                    })
                ]
            }),
            new ol.layer.Group({
                // A layer must have a title to appear in the layerswitcher
                title: 'Overlays',
                // Adding a 'fold' property set to either 'open' or 'close' makes the group layer
                // collapsible
                fold: 'open',
                layers: params.overlays.map( function (overlay) {
                    return new ol.layer.Tile({
                        // A layer must have a title to appear in the layerswitcher
                        title: overlay.title,
                        visible: overlay.visible,
                        source: new ol.source.TileWMS({
                            url: overlay.url,
                            params: {
                                LAYERS: overlay.filename,
                                VERSION: '1.1.1'
                            }
                        })
                    })
                } )
            })
        ];

        var map = new ol.Map({
            controls: ol.control.defaults().extend([
                new ol.control.ScaleLine({
                    units: 'degrees'
                })
            ]),
            layers: layers,
            target: 'map',
            view: new ol.View({
                projection: 'EPSG:4326',
            })
        });
        map.getView().fit(ol.extent.boundingExtent([
            [params.bbox.minX, params.bbox.minY], [params.bbox.maxX, params.bbox.maxY]
        ]), map.getSize());

        var layerSwitcher = new ol.control.LayerSwitcher({
            tipLabel: 'Légende', // Optional label for button
            groupSelectStyle: 'children' // Can be 'children' [default], 'group' or 'none'
        });
        map.addControl(layerSwitcher);
    }

    return {
        init: init
    }
})();