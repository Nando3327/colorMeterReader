export interface ReaderInterfacePlugin {
  echo(options: { value: string }): Promise<{ value: string }>;
  listPairedDevices(): { devices: any[] }
  connect(options: { value: string }): Promise<{ value: boolean }>
  disconnect(options: { value: string }): Promise<{ value: boolean }>
  valueDetected(): Promise<{ value: any }>
  isReaderConnected(): Promise<{ value: boolean }>
  getReaderCalibrationStatus(): Promise<{ black: boolean, white: boolean }>
}
