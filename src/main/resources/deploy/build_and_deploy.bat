@echo off
setlocal

set directories=crawler_core
@REM set directories=gyu-hee member message_publisher telegram-bot

for %%d in (%directories%) do (
    echo Building project in directory: %%d

    cd %%d

    call gradlew build

    if errorlevel 1 (
        echo Build failed in directory: %%d
        exit /b 1
    )

    cd ..
)
echo Rebuild docker compose
docker-compose build --no-cache


echo Stopping existing Docker Compose services...
docker-compose down

echo Starting Docker Compose
docker-compose up -d

endlocal

pause