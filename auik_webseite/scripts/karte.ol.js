 var map = new ol.Map({
        target: 'map',
        layers: [
          new ol.layer.Tile({
            source: new ol.source.MapQuest({layer: 'sat'})
          })
        ],
        view: new ol.View2D({
          center: ol.proj.transform([8, 52], 'EPSG:4326', 'EPSG:3857'),
          zoom: 4
        })
      });
