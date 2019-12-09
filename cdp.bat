@echo off
start cmd /k "D:\IdeaProject\Redis\redis-server.exe D:\IdeaProject\Redis\redis.windows.conf"
start /d "D:\IdeaProject\Redis\SAEA.WebRedisManager v4.5.6.8\Net\" SAEA.WebRedisManagerForNet.exe 
exit
