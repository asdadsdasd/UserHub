# ./nginx/Dockerfile
FROM openresty/openresty:alpine

# Копируем конфиг в правильное место
COPY nginx.conf /usr/local/openresty/nginx/conf/nginx.conf

# Копируем Lua-скрипты
COPY lua/ /etc/nginx/lua/

# Создаем каталог для кеша
RUN mkdir -p /var/cache/nginx
