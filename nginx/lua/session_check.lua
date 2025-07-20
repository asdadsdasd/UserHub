local redis = require "resty.redis"
local red = redis:new()

red:set_timeout(1000)
local ok, err = red:connect("redis", 6379)
if not ok then
    ngx.log(ngx.ERR, "Redis connection failed: ", err)
    return ngx.redirect("/login") -- Если Redis недоступен, редирект
end

local cookie = ngx.var.http_cookie
if not cookie then
    return ngx.redirect("/login")
end

local session_id = string.match(cookie, "SESSION=([^;]+)")
if not session_id then
    return ngx.redirect("/login")
end

local key = "spring:session:sessions:" .. session_id
local res, err = red:exists(key)
if res == 0 then
    return ngx.redirect("/login")
end

-- Всё хорошо, продолжаем
