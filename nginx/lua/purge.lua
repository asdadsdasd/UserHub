local handle = io.popen("rm -rf /var/cache/nginx/*")
if handle then
    handle:close()
end

ngx.say("Cache cleared")
