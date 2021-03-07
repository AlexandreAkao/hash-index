import { FC, useCallback } from 'react';
import { useDropzone } from 'react-dropzone';

import { IDropzone } from './types';

import './styles.css';

const Dropzone: FC<IDropzone> = ({ onload }) => {
  const onDrop = useCallback((acceptedFiles: File[]) => {
    if (acceptedFiles[0].type !== 'text/plain') return;

    acceptedFiles.forEach((file: any) => {
      const reader = new FileReader()

      reader.onabort = () => console.log('file reading was aborted')
      reader.onerror = () => console.log('file reading has failed')
      reader.onload = () => {
      // Do whatever you want with the file contents
        const binaryStr = reader.result
        console.log(binaryStr)

        onload && onload(file);
      }
      reader.readAsArrayBuffer(file)
    })
    
  }, [onload])

  const {getRootProps, getInputProps} = useDropzone({ 
    onDrop, 
    multiple: false,
    accept: 'text/plain'
  })

  return (
    <div className="dropzone" {...getRootProps()}>
      <input {...getInputProps()} />
      <p className="dropzone-advice">Arraste e solte o arquivo ou clique para selecionar o arquivo</p>
      <p className="dropzone-observation">Obs: Apenas arquivos .txt</p>
    </div>
  )
}

export default Dropzone;