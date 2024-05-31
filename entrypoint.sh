#!/bin/bash

# Start Elasticsearch
/usr/share/elasticsearch/bin/elasticsearch &

# Wait for Elasticsearch to start
until curl -s localhost:9200; do
  sleep 1
done

# Create index template
curl -X PUT "localhost:9200/_index_template/my_default_template" -H 'Content-Type: application/json' -d @/usr/share/elasticsearch/config/index_template.json

# Keep container running
tail -f /dev/null