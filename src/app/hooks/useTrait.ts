import { useState } from 'react';

export default function useTrait(initialValue: any) {
   const [trait, updateTrait] = useState(initialValue);

   let current = trait;

   const get = () => current;

   const set = (newValue: any) => {
      current = newValue;
      updateTrait(newValue);
      return current;
   }

   return {
      get,
      set,
   }
}