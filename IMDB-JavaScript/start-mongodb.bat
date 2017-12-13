@echo off

echo Starting MongoDB...

mkdir data

mongod --dbpath=data

:finish

pause
