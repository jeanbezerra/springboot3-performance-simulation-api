#!/bin/bash

TIMESTAMP=$(date +%Y%m%d_%H%M%S)
DUMP_DIR="/opt/software/dumps"
THREAD_DUMP="$DUMP_DIR/threaddump-$TIMESTAMP.txt"
LOG_FILE="$DUMP_DIR/oom.log"

mkdir -p "$DUMP_DIR"

PID=$(jps | grep app.jar | awk '{print $1}')

if [ -n "$PID" ]; then
  echo "[OOM Handler] $(date) - Dumping threads from PID $PID" >> "$LOG_FILE"
  jcmd "$PID" Thread.print > "$THREAD_DUMP"
else
  echo "[OOM Handler] $(date) - PID not found. Thread dump skipped." >> "$LOG_FILE"
fi