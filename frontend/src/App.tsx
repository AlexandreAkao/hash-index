import { useState, ChangeEvent, useEffect } from 'react';
import { ToastContainer, toast } from 'react-toastify';
import { FaSearch } from 'react-icons/fa';

import { IConfiguration } from './model/Configuration';
import Dropzone from "./components/Dropzone";
import InfoNumber from './components/InfoNumber';
import api from './services/api';
import { toastConfig } from './config/toast';

import 'react-toastify/dist/ReactToastify.css';
import './App.css';

function App() {
  const [pageSize, setPageSize] = useState(0);
  const [searchReg, setSearchReg] = useState('');
  const [bucketSize, setBucketSize] = useState(0);
  const [info, setInfo] = useState({
    readSize: 0,
    pageSize: 0,
    bucketSize: 0,
    colisionCount: 0,
    overflowCount: 0,
    cost: 0,
    bucketNumber: 0,
})
  
  useEffect(() => {
    api.get<Omit<IConfiguration, 'cost'>>('table').then(data => {
      const { 
        bucketSize,
        pageSize,
        colisionCount,
        overflowCount,
        readSize,
        bucketNumber
      } = data.data

      setInfo(prev => ({
        ...prev, 
        pageSize,
        bucketSize,
        colisionCount,
        overflowCount,
        readSize,
        bucketNumber
      }))

      setPageSize(pageSize);
      setBucketSize(bucketSize);
    }).catch(_ => {
      api.get<Pick<IConfiguration, 'pageSize' | 'bucketSize'>>('configuration').then(data => {
        const { bucketSize, pageSize } = data.data;

        setInfo(prev => ({
          ...prev, 
          pageSize,
          bucketSize,
        }))
  
        setPageSize(pageSize);
        setBucketSize(bucketSize);
      })
    })
  }, [])

  const onChangePage = (event: ChangeEvent<HTMLInputElement>) => {
    const page = Number(event.target.value);
    if (isNaN(page)) return; 
    setPageSize(page);
  }

  const onChangeBucket = (event: ChangeEvent<HTMLInputElement>) => {
    const bucket = Number(event.target.value);
    if (isNaN(bucket)) return; 
    setBucketSize(bucket);
  }

  const onSubmitConfig = () => {
    api.post('configuration', {
      pageSize,
      bucketSize
    }).then(data => {
      if (data.status === 201) {
        setInfo(prev => ({
          ...prev, 
          pageSize,
          bucketSize, 
        }))
        toast.success('Configuração salva', toastConfig)
      } else {
        toast.error('Backend offline', toastConfig)
      }
    }).catch(error => {
      toast.error('Backend offline: ' + error, toastConfig)
    })
  }

  const onloadFile = (file: File) => {
    const formData = new FormData();
    formData.append('file', file);

    api.post<Omit<IConfiguration, 'cost'>>('table', formData).then(data => {
      const { 
        bucketSize, 
        pageSize, 
        readSize, 
        colisionCount, 
        overflowCount, 
        bucketNumber 
      } = data.data;

      setInfo(prev => ({
        ...prev, 
        pageSize,
        bucketSize,
        readSize,
        colisionCount,
        overflowCount,
        bucketNumber
      }));

      toast.success('Tabela lida com sucesso', toastConfig)
    }).catch(error => {
      toast.error('Backend offline: ' + error, toastConfig)
    })
  }

  const onSearchReg = () => {
    api.get<Pick<IConfiguration, 'cost'>>('table/search', {
      params: {
        search: searchReg
      }
    }).then(data => {
      const { cost } = data.data;

      setInfo(prev => ({
        ...prev,
        cost
      }))
    }).catch(error => {
      toast.error('Backend offline: ' + error, toastConfig)
    })
  }

  const onChangeSearch = (event: ChangeEvent<HTMLInputElement>) => {
    const { value } = event.target;
    setSearchReg(value);
  }

  return (
    <div className="App">
      <ToastContainer/>
      <section className="container">
        <div className="dropzone-container">
          <Dropzone onload={onloadFile} />
        </div>
        <div className="config-container">
          <div className="config-container-inputs">
            <div>
              <label htmlFor="">Tamanho da página: </label>
              <input type="text" value={pageSize} onChange={onChangePage} />
            </div>
            <div>
              <label htmlFor="">Tamanho do bucket: </label>
              <input type="text" value={bucketSize} onChange={onChangeBucket} />
            </div>
          </div>
          <button className="config-save-btn" onClick={onSubmitConfig}>Salvar</button>
        </div>
        <div className="search-container">
          <input 
            className="search-input-btn" 
            type="text" 
            placeholder="Chave de busca" 
            value={searchReg}
            onChange={onChangeSearch}
          />
          <button
            onClick={onSearchReg}
            className="search-submit-btn"
            value="Buscar"
          >
            <FaSearch className="search-submit-icon" />
          </button>
        </div>
        <div className="database-info">
          <InfoNumber title="Tamanho da página" number={info.pageSize} />
          <InfoNumber title="Tamanho do bucket" number={info.bucketSize} />
          <InfoNumber title="Número de colisões" number={info.colisionCount} />
          <InfoNumber title="Número de overflows" number={info.overflowCount} />
          <InfoNumber title="Quantidade de registros" number={info.readSize} />
          <InfoNumber title="Custo da leitura" number={info.cost} />
          <InfoNumber title="Quantidade de buckets" number={info.bucketNumber} />
        </div>
      </section>
    </div>
  );
}

export default App;
