FROM openresty/openresty:1.25.3.1-alpine-fat

RUN apk add --no-cache git

RUN git clone https://github.com/ledgetech/lua-resty-http.git /tmp/lua-resty-http && \
    mkdir -p /usr/local/openresty/lualib/resty && \
    cp /tmp/lua-resty-http/lib/resty/http*.lua /usr/local/openresty/lualib/resty/ && \
    rm -rf /tmp/lua-resty-http

RUN mkdir -p /var/cache/nginx && \
    chown -R nobody /var/cache/nginx

RUN ln -sf /dev/stdout /usr/local/openresty/nginx/logs/access.log && \
    ln -sf /dev/stderr /usr/local/openresty/nginx/logs/error.log
