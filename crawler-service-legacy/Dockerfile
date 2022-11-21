FROM python:3.9.15-slim-bullseye

WORKDIR /app

# Install dependencies
COPY ./crawler-service/requirements.txt .

RUN pip install --no-cache-dir -r requirements.txt

# Copy source folder
COPY ./crawler-service .

# Register API to Eureka server and run application
CMD python eureka_client.py && uvicorn main:app --reload --host 0.0.0.0
