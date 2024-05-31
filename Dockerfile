# Elasticsearch Base Image
FROM docker.elastic.co/elasticsearch/elasticsearch:8.13.4

# Disable Security
ENV ELASTICSEARCH_USERNAME=elastic
ENV ELASTICSEARCH_PASSWORD=changeme

# Copy Configuration Files
COPY elasticsearch.yml /usr/share/elasticsearch/config/
COPY synonyms.txt /usr/share/elasticsearch/config/analysis/synonyms.txt
COPY index_template.json /usr/share/elasticsearch/config/index_template.json
COPY entrypoint.sh /usr/local/bin/entrypoint.sh

# Make entrypoint.sh executable
CMD ["chmod", "+x", "/usr/local/bin/entrypoint.sh"]

# Expose Ports
EXPOSE 9200 9300

# Set entrypoint
ENTRYPOINT ["/usr/local/bin/entrypoint.sh"]