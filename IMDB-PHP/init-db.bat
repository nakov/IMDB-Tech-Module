echo Restoring composer packages...

php composer.phar install --no-scripts --no-progress -q

echo Initializing database...

php bin/console doctrine:database:create --if-not-exists
php bin/console doctrine:schema:update --force

echo Successfully initialized database!

pause

:finish
