import { FC } from 'react';
import CountUp from 'react-countup';

import { IInfoNumbe } from './types';

import './styles.css';

const InfoNumber: FC<IInfoNumbe> = ({
  title,
  number
}) => {

  return (
    <div className="info-number-container">
      <span className="info-title">{title}</span>
      <CountUp
        start={0}
        end={number}
        separator="."
        duration={2}
      >
        {({ countUpRef }) => (
          <span ref={countUpRef} className="info-number" />
        )}
      </CountUp>
      
    </div>
  )
}

export default InfoNumber