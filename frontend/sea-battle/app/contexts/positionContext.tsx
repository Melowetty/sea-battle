import { createContext, useContext, useState} from 'react';

interface Position {
    x: number;
    y: number;
    tag: string;
}

interface PositionContextType {
    position: Position;
    updatePosition: (newPosition: Partial<Position>) => void;
}

export const PositionContext = createContext<PositionContextType>({
    position: { x: 0, y: 0, tag: '' },
    updatePosition: () => {},
});

export const PositionProvider = ({ children }: { children: ReactNode }) => {
    const [position, setPosition] = useState<Position>({ x: 0, y: 0, tag: '' });

    const updatePosition = (newPosition: Partial<Position>) => {
        setPosition(prev => ({ ...prev, ...newPosition }));
    };

    return (
    <PositionContext.Provider value={{ position, updatePosition }}>
        {children}
    </PositionContext.Provider>
);
};

export const usePosition = () => {
    const context = useContext(PositionContext);
    if (!context) {
        throw new Error('usePosition must be used within a PositionProvider');
    }
    return context;
};