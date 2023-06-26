import React from 'react';

function data({value}) {
    const styles = {
        position: 'absolute',
        left: '70vw',
        alignSelf: 'center',

        fontFamily: 'Inter',
        fontStyle: 'normal',
        fontWeight: '300',
        lineHeight: '5px',
      };

      var x = value;

  return (
    <p style={styles} id='requestName'>{x}</p>
  );
}

export default data;